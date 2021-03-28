import '../setupTests';
import { render, screen, waitFor } from '@testing-library/react';
import { createMemoryHistory } from 'history';
import { Router } from 'react-router-dom';
import Inventory from '../../views/Inventory';

import { rest } from 'msw'
import { setupServer } from 'msw/node'

beforeEach(() => {
  Object.defineProperty(window, "localStorage", {
    value: {
      getItem: jest.fn(x => x === "userId" ? '1' : ''),
      setItem: jest.fn(() => null)
    },
    writable: true
  });
  process.env = Object.assign(process.env, { REACT_APP_BACKEND_URL: 'http://localhost:8080' });
});

test('Inventory page shows login button if not logged in', () => {
  const history = createMemoryHistory(); // mocking Location and History fields
  render(<Router history={history}>
    <Inventory loggedIn={false} />
  </Router>
  )
  const loginTxt = screen.getByText(/Please login to view your inventory/i);
  expect(loginTxt).toBeInTheDocument();

  const loginBtn = document.getElementById('loginBtn');
  expect(loginBtn).toBeInTheDocument();
});

test('Inventory page shows inventory', async () => {
  const server = setupServer(
    rest.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/1/alcohol`, (req, res, ctx) => {
      return res(
        ctx.status(200),
        ctx.json([{ name: "Vodka" }, { name: "Rum" }]),
      );
    }),
    rest.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/1/modifier`, (req, res, ctx) => {
      return res(
        ctx.status(200),
        ctx.json([{ name: "Coke" }, { name: "Lime Juice" }]),
      );
    })
  )
  server.listen();
  const history = createMemoryHistory();
  render(
    <Router history={history}>
      <Inventory loggedIn={true} />
    </Router>
  )

  await waitFor(() => expect(window.localStorage.getItem).toHaveBeenCalledWith("userId"));

  await waitFor(() => screen.getByText(/Vodka/i));
  await waitFor(() => screen.getByText(/Rum/i));
  await waitFor(() => screen.getByText(/Coke/i));
  await waitFor(() => screen.getByText(/Lime Juice/i));

  server.close()
});