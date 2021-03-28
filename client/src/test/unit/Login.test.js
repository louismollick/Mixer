import '../setupTests';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { createMemoryHistory } from 'history';
import { Router } from 'react-router-dom';
import Login from '../../views/Login';

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
  process.env = Object.assign(process.env, { REACT_APP_BACKEND_URL: 'http://localhost:8080' });
});

test('Login page renders correct fields', () => {
  const history = createMemoryHistory(); // mocking Location and History fields
  render(
    <Router history={history}>
      <Login />
    </Router>
  )
  const emailField = screen.getByLabelText(/Email Address/i);
  expect(emailField).toBeInTheDocument();

  const passwordField = screen.getByLabelText(/Password/i);
  expect(passwordField).toBeInTheDocument();

  const button = screen.getByRole('button', { type: /submit/i });
  expect(button).toBeInTheDocument();
});

test('Login page shows message after signup', () => {
  const history = createMemoryHistory();
  history.push('/login?signup=true')
  render(
    <Router history={history}>
      <Login />
    </Router>
  )
  const message = screen.getByText(/Signup Successful! Please login with your credentials./i);
  expect(message).toBeInTheDocument();
});

test('Login page button disabled until fields inputted', () => {
  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Login />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i });
  expect(button.disabled).toBeTruthy();

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })

  expect(button.disabled).toBeFalsy();
});

test('Login page sets token in localStorage after succesful login and redirects', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/login`, (req, res, ctx) => {
      return res(
        ctx.status(200),
        ctx.json({ token: 'token', email: 'email', userId: 'userId' }),
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  history.replace('/login');
  render(
    <Router history={history}>
      <Login setLoggedIn={jest.fn()} />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i, name: 'Login' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  await waitFor(() => expect(window.localStorage.setItem).toHaveBeenCalled());

  expect(history.length).toBe(2); // redirect

  server.close()
});

test('Login page shows error after incorrect login', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/login/`, (req, res, ctx) => {
      return res(
        ctx.status(403)
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Login setLoggedIn={jest.fn()} />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i, name: 'Login' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  await waitFor(() => expect(screen.getByText(/Invalid credentials. Please try again./i)).toBeInTheDocument());

  server.close()
});

test('Login page shows error after server error', async () => {
  const server = setupServer(
    rest.post(`${process.env.REACT_APP_BACKEND_URL}/login/`, (req, res, ctx) => {
      return res(
        ctx.status(500)
      );
    })
  )
  server.listen();

  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Login setLoggedIn={jest.fn()} />
    </Router>
  )
  const button = screen.getByRole('button', { type: /submit/i, name: 'Login' });

  const email = document.getElementById('email');
  fireEvent.change(email, { target: { value: '1' } })
  const password = document.getElementById('password');
  fireEvent.change(password, { target: { value: '1' } })
  fireEvent.click(button);

  await waitFor(() => expect(screen.getByText(/Something went wrong. Please refresh or try again later./i)).toBeInTheDocument());

  server.close()
});