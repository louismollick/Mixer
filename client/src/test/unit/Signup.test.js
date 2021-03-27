import '../setupTests';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { createMemoryHistory } from 'history';
import { Router } from 'react-router-dom';
import Signup from '../../views/Signup';

import { rest } from 'msw'
import { setupServer } from 'msw/node'

beforeEach(() => {
  Object.defineProperty(window, "localStorage", {
    value: {
      getItem: jest.fn(() => null),
      setItem: jest.fn(() => null)
    },
    writable: true
  });
  delete window.location;
  window.location = { replace: jest.fn() };
  process.env = Object.assign(process.env, { REACT_APP_BACKEND_URL: 'http://localhost:8080' });
});

test('Signup page renders correct fields', () => {
  const history = createMemoryHistory(); // mocking Location and History fields
  render(
    <Router history={history}>
      <Signup />
    </Router>
  )
  const emailField = screen.getByLabelText(/Email Address/i);
  expect(emailField).toBeInTheDocument();

  const passwordField = screen.getByLabelText(/Password/i);
  expect(passwordField).toBeInTheDocument();

  const button = screen.getByRole('button', { type: /submit/i });
  expect(button).toBeInTheDocument();
});

test('Signup page redirects after successful signup', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/signup`, (req, res, ctx) => {
      return res(
        ctx.status(200)
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  history.replace('/signup');
  render(
    <Router history={history}>
      <Signup setLoggedIn={jest.fn()} />
    </Router>
  )

  const spy = jest.spyOn(window.location, 'replace');
  const button = screen.getByRole('button', { type: /submit/i, name: 'Sign Up' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  // redirect
  await waitFor(() => {
    expect(spy).toHaveBeenCalledWith('/login?signup=true');
  });

  server.close()
});

test('Signup page shows error after incorrect Signup', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/signup`, (req, res, ctx) => {
      return res(
        ctx.status(400)
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Signup setLoggedIn={jest.fn()} />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i, name: 'Sign Up' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  await waitFor(() => screen.getAllByText(/Password must be at least 8 characters!/i));

  server.close()
});

test('Signup page shows error after email already used', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/signup`, (req, res, ctx) => {
      return res(
        ctx.status(409)
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Signup setLoggedIn={jest.fn()} />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i, name: 'Sign Up' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  await waitFor(() => screen.getAllByText(/Looks like that email address already has an account!/i));

  server.close()
});