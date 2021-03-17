import { useState, useEffect } from 'react';
import { Redirect, useLocation } from 'react-router-dom';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

const Alert = (props) => {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const Login = ({ setLoggedIn }) => {
  const location = useLocation();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showSignup, setShowSignup] = useState(false);
  const [showLoginError, setShowLoginError] = useState(false);
  const [showError, setShowError] = useState(false);
  const [redirect, setRedirect] = useState(false);
  const classes = useStyles();

  const formNotEmpty = () => {
    return email.length > 0 && password.length > 0;
  }

  const handleClose = (setFunc) => (event, reason) => {
    if (reason === 'clickaway') return;
    setFunc(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post(`${process.env.REACT_APP_BACKEND_URL}/login`, {
      email,
      password
    }).then(res => {
      if (res.status === 200) {
        console.log("HERE:", res);
        window.localStorage.setItem('token', res.data.token);
        window.localStorage.setItem('email', res.data.email);
        window.localStorage.setItem('userId', res.data.userId);
        setLoggedIn(true);
        setRedirect(true);
      } else {
        setShowLoginError(true);
      }
    })
      .catch(e => {
        if (e.response.status === 403)
          setShowLoginError(true);
        else {
          console.error(e);
          setShowError(true);
        }
      })
  }

  useEffect(() => {
    if (new URLSearchParams(location.search).get("signup"))
      setShowSignup(true);
  }, [location]);

  if (redirect)
    return <Redirect push to={{
      pathname: "/",
      state: { redirect: 'login' }
    }} />

  return (
    <>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Login
        </Typography>
          <form className={classes.form} noValidate onSubmit={handleSubmit}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="current-password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              disabled={!formNotEmpty()}
            >
              Login
          </Button>
            <Grid container justify="flex-end">
              <Grid item>
                <Link href="/signup" variant="body2">
                  {"Don't have an account? Sign up"}
                </Link>
              </Grid>
            </Grid>
          </form>
        </div>
      </Container>
      <Snackbar open={showSignup} autoHideDuration={6000} onClose={handleClose(setShowSignup)}>
        <Alert onClose={handleClose(setShowSignup)} severity="success">
          Signup Successful! Please login with your credentials.
        </Alert>
      </Snackbar>
      <Snackbar open={showLoginError} autoHideDuration={10000} onClose={handleClose(setShowLoginError)}>
        <Alert onClose={handleClose(setShowLoginError)} severity="error">
          Invalid credentials. Please try again.
        </Alert>
      </Snackbar>
      <Snackbar open={showError} autoHideDuration={null} onClose={handleClose(setShowError)}>
        <Alert onClose={handleClose(setShowError)} severity="error">
          Something went wrong. Please refresh or try again later.
        </Alert>
      </Snackbar>
    </>
  );
}

export default Login;