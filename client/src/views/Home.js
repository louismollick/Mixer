<<<<<<< HEAD
import { useState, useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import Typography from '@material-ui/core/Typography';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

const Alert = (props) => {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const Home = () => {
  const location = useLocation();
  const history = useHistory();
  const [showLoginSuccess, setShowLoginSuccess] = useState(false);
  const [showLogoutMessage, setShowLogoutMessage] = useState(false);

  useEffect(() => {
    if (!location.state) return;
    if (location.state.redirect === 'login')
      setShowLoginSuccess(true);
    else if (location.state.redirect === 'logout')
      setShowLogoutMessage(true);
    history.replace(); // clear location state, so message doesn't appear after refresh
  }, [location, history]);

  const handleClose = (setFunc) => (event, reason) => {
    if (reason === 'clickaway') return;
    setFunc(false);
  };

=======
import React from 'react';
import Typography from '@material-ui/core/Typography';

const Home = () => {
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
  return (
    <div>
      <Typography component="h1" variant="h5" align="center">
        Welcome to mixer, the best cocktail generator on the internet
      </Typography>
<<<<<<< HEAD
      <Snackbar open={showLoginSuccess} autoHideDuration={6000} onClose={handleClose(setShowLoginSuccess)}>
        <Alert onClose={handleClose(setShowLoginSuccess)} severity="success">
          Successfully logged in!
        </Alert>
      </Snackbar>
      <Snackbar open={showLogoutMessage} autoHideDuration={6000} onClose={handleClose(setShowLogoutMessage)}>
        <Alert onClose={handleClose(setShowLogoutMessage)} severity="info">
          Successfully logged out.
        </Alert>
      </Snackbar>
=======
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
    </div>
  )
};

export default Home;
