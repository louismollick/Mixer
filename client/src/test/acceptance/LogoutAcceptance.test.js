import '../setupTests';
import { loadFeature, defineFeature } from 'jest-cucumber';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { createMemoryHistory } from 'history';
import { Router } from 'react-router-dom';
import Profile from '../../views/Profile';

const feature = loadFeature('./src/test/acceptance/gherkin/ID16_Logout.feature');

defineFeature(feature, (test) => {

  beforeEach(() => {
    Object.defineProperty(window, "localStorage", {
      value: {
        getItem: jest.fn(() => null),
        removeItem: jest.fn(() => null),
      },
      writable: true
    });
  });

  test('User successfully logged out of account (Success Flow)', ({ given, when, then }) => {
    let loggedIn;
    let button;
    let history;
    given('I am signed up for Mixer', () => {
      // Handle on backend, use default user
    });

    given('I am logged into my account', () => {
      loggedIn = true;
    });

    given('I am on the Profile page', () => {
      history = createMemoryHistory();
      history.replace('/profile');
      render(
        <Router history={history}>
          <Profile loggedIn={loggedIn} setLoggedIn={jest.fn()}/>
        </Router>
      )

      button = screen.getByText(/Logout/i);
      expect(button).toBeInTheDocument();
    });

    when('I press logout button', () => {
      fireEvent.click(button);
    });

    then('I am no longer authenticated', async () => {
      await waitFor(() => expect(window.localStorage.removeItem).toHaveBeenCalledTimes(3));
    });

    then('I am redirected to the home page', () => {
      expect(history.length).toBe(2); // redirect./
    });
  });

  test('User was not logged in (Alternate Flow)', ({ given, when, then }) => {
    let loggedIn;
    given('I am not logged in into my account', () => {
      loggedIn = false;
    });

    when('I navigate to the Profile page', () => {
      const history = createMemoryHistory();
      render(
        <Router history={history}>
          <Profile loggedIn={loggedIn} />
        </Router>
      )
    });

    then('I will not see a logout button', async () => {
      await waitFor(() => expect(screen.queryByText(/Logout/i)).not.toBeInTheDocument());

    });

    then('I will see the message \'Please login to see your profile.\'', async (message) => {
      await waitFor(() => expect(screen.getByText(/Please login to see your profile./i)).toBeInTheDocument());
    });
  });
});