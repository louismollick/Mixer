import { useState } from 'react';
import { Redirect } from 'react-router-dom';
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

  return (
    <div>
      <Typography component="h1" variant="h5" align="center">
        Welcome, {window.localStorage.getItem('email')}
      </Typography>
      <Box display="flex" alignItems="center" justifyContent="center" >
        <Button variant="contained" color="primary" onClick={handleLogout}>Logout</Button>
      </Box>
    </div >
  )
};

export default Profile;