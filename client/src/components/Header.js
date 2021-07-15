
import { useLocation, withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Link from '@material-ui/core/Link';

import LocalBarIcon from '@material-ui/icons/LocalBar';

const useStyles = makeStyles((theme) => ({
  toolbar: {
    borderBottom: `1px solid ${theme.palette.divider}`,
    justifyContent: 'center'
  },
  toolbarSecondary: {
    justifyContent: 'space-around',
    overflowX: 'auto',
  },
  toolbarLink: {
    padding: theme.spacing(1),
    flexShrink: 0,
  },
  icon: {
    marginRight: '0.2em'
  }
}));

const notLoggedInPaths = ['/login', '/signup'];
const loggedInPaths = ['/profile'];

const Header = ({ sections, title, loggedIn }) => {
  const location = useLocation();
  const classes = useStyles();
  const isVisible = !(notLoggedInPaths.includes(location.pathname));

  return (isVisible &&
    <>
      <Toolbar className={classes.toolbar}>
        <Box display="flex" alignItems="center">
          <LocalBarIcon fontSize="large" className={classes.icon}/>
          <Typography
            component="h2"
            variant="h5"
            color="inherit"
            align="center"
            noWrap
            className={classes.toolbarTitle}
          >
            {title}
          </Typography>
        </Box>
      </Toolbar>
      <Toolbar component="nav" variant="dense" className={classes.toolbarSecondary}>
        {sections
          .filter(section => loggedIn ? !notLoggedInPaths.includes(section.url) : !loggedInPaths.includes(section.url))
          .map((section) => (
            <Link
              color="inherit"
              noWrap
              key={section.title}
              variant="body2"
              href={section.url}
              className={classes.toolbarLink}
            >
              {section.title}
            </Link>
          ))}
      </Toolbar>
    </>
  );
}

Header.propTypes = {
  sections: PropTypes.array,
  title: PropTypes.string,
};

export default withRouter(Header); // to have access to location prop