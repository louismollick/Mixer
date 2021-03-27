import '../setupTests';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { createMemoryHistory } from 'history';
import { Router } from 'react-router-dom';
import Profile from '../../views/Profile';

beforeEach(() => {
  Object.defineProperty(window, "localStorage", {
    value: {
      getItem: jest.fn(() => null),
      removeItem: jest.fn(() => null),
    },
    writable: true
  });
});

test('Profile page renders correct fields', () => {
  const history = createMemoryHistory(); // mocking Location and History fields
  render(
    <Router history={history}>
      <Profile loggedIn={true}/>
    </Router>
  )
  const welcome = screen.getByText(/Welcome/i);
  expect(welcome).toBeInTheDocument();

  const button = screen.getByText(/Logout/i);
  expect(button).toBeInTheDocument();
});

test('Profile page logout button clears localStorage', async () => {
  const history = createMemoryHistory(); // mocking Location and History fields
  render(
    <Router history={history}>
      <Profile loggedIn={true} setLoggedIn={jest.fn()}/>
    </Router>
  )

  const button = screen.getByText(/Logout/i);
  expect(button).toBeInTheDocument();

  fireEvent.click(button);

  await waitFor(() => expect(window.localStorage.removeItem).toHaveBeenCalledTimes(3));
});