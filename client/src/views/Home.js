import { useState, useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import Inventory from '../components/Inventory'
import StrengthTypeRadio from '../components/StrengthTypeRadio';
import ServingSizeRadio from '../components/ServingSizeRadio';
import TasteTypesSelect from '../components/TasteTypesSelect';
import { Link } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  root: {
    marginRight: '3rem',
    marginLeft: '3rem',
  },
  button: {
    margin: '2rem'
  }
}));

const Home = ({ loggedIn, searchOptions:
  { alcohols,
    setAlcohols,
    modifiers,
    setModifiers,
    tasteTypes,
    setTasteTypes,
    strengthType,
    setStrengthType,
    servingSize,
    setServingSize }
}) => {
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

  return (
    <div className={classes.root}>
      <Box display="flex" justifyContent="center" className={classes.button}>
        <Link to="/search">
          <Button variant="contained" color="primary" >Search for Cocktails</Button>
        </Link>
      </Box>
      <TasteTypesSelect tasteTypes={tasteTypes} setTasteTypes={setTasteTypes} />
      <StrengthTypeRadio strengthType={strengthType} setStrengthType={setStrengthType} />
      <ServingSizeRadio servingSize={servingSize} setServingSize={setServingSize} />
      <Inventory loggedIn={loggedIn} alcohols={alcohols} modifiers={modifiers} setAlcohols={setAlcohols} setModifiers={setModifiers} />

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
    </div>
  )
};

const Alert = (props) => {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default Home;
