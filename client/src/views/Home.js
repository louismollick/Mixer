import React, { useState, useEffect } from 'react';
import { useHistory, useLocation, Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import Snackbar from '@material-ui/core/Snackbar';
import Typography from '@material-ui/core/Typography';
import MuiAlert from '@material-ui/lab/Alert';
import InventoryItemList from '../components/InventoryItemList';
import Grid from '@material-ui/core/Grid';
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';

const useStyles = makeStyles({
  root: {
    flexGrow: 1,
    maxWidth: '1140px',
    margin: 'auto'
  },
  arrow: {
    marginLeft: '0.25em'
  },
  instructions: {
    textAlign: 'center'
  }
});

const Home = ({ loggedIn }) => {
  const location = useLocation();
  const history = useHistory();
  const classes = useStyles();

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

  if (!loggedIn)
    return (
      <>
        <Typography variant="h5" align="center">
          Please login to view your inventory.
        </Typography>
        <Typography variant="body1" align="center">
          For testing purposes, you can use the email "user@example.com" with password "12345678" if you don't want to make an account.
        </Typography>
        <Box display="flex" alignItems="center" justifyContent="center" >
          <Button id="loginBtn" variant="contained" color="primary" component={Link} to={'/login'}>Login</Button>
        </Box>
        <Snackbar open={showLogoutMessage} autoHideDuration={6000} onClose={handleClose(setShowLogoutMessage)}>
          <Alert onClose={handleClose(setShowLogoutMessage)} severity="info">
            Successfully logged out.
          </Alert>
        </Snackbar>
      </>
    );

  return (
    <div className={classes.root}>
      <Grid container spacing={1} justifyitems="center">
        <Grid item xs={12}>
          <Button variant="contained" color="primary" component={Link} to={'/search'} fullWidth>
            Search for Cocktails
            <ArrowForwardIcon className={classes.arrow} />
          </Button>
        </Grid>
        <Grid item xs={12}>
          <InventoryItemList itemType="alcohol" />
        </Grid>
        <Grid item xs={12}>
          <InventoryItemList itemType="modifier" />
        </Grid>

        <Snackbar open={showLoginSuccess} autoHideDuration={6000} onClose={handleClose(setShowLoginSuccess)}>
          <Alert onClose={handleClose(setShowLoginSuccess)} severity="success">
            Successfully logged in!
          </Alert>
        </Snackbar>
      </Grid>
    </div>
  )
};

const Alert = (props) => {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default Home;
