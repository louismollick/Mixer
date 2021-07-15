import { useState } from 'react';
import { Redirect, Link } from 'react-router-dom';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';


const Profile = ({ loggedIn, setLoggedIn }) => {
  const [redirect, setRedirect] = useState(false);

  const handleLogout = () => {
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('email');
    window.localStorage.removeItem('userId');
    setLoggedIn(false);
    setRedirect(true);
  }

  if (redirect)
    return <Redirect push to={{
      pathname: "/",
      state: { redirect: 'logout' }
    }} />;

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
      </>
    );

  return (
    <div>
      <Typography component="h2" variant="h5" align="center">
        Welcome, {window.localStorage.getItem('email')}
      </Typography>
      <Box display="flex" alignItems="center" justifyContent="center" >
        <Button variant="contained" color="primary" onClick={handleLogout}>Logout</Button>
      </Box>
    </div >
  )
};

export default Profile;