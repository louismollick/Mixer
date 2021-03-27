import { useState, useEffect } from 'react';
import { Route, Switch } from "react-router-dom";
import Home from "./views/Home";
import Login from './views/Login';
import Signup from './views/Signup';
import Profile from './views/Profile';
import Inventory from './views/Inventory';
import Search from './views/Search';
import Header from './components/Header';

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  const sections = [
    { title: 'Inventory', url: '/inventory' },
    { title: 'Search', url: '/search' },
    { title: 'Login', url: '/login' },
    { title: 'Signup', url: '/signup' },
    { title: 'Profile', url: '/profile' }
  ];

  useEffect(() => {
    if (window.localStorage.getItem('token') != null)
      setLoggedIn(true);
  }, []);

  return (
    <div className="App">
      <Header title="Mixer" sections={sections} loggedIn={loggedIn} />
      <Switch>
        <Route exact path="/"><Home /></Route>
        <Route path="/inventory"><Inventory loggedIn={loggedIn}/></Route>
        <Route path="/search"><Search /></Route>
        <Route path="/login"><Login setLoggedIn={setLoggedIn} /></Route>
        <Route path="/signup"><Signup /></Route>
        <Route path="/profile"><Profile loggedIn={loggedIn} setLoggedIn={setLoggedIn} /></Route>
      </Switch >
    </div >
  );
}

export default App;
