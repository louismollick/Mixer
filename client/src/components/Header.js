<<<<<<< HEAD
import { useLocation } from 'react-router-dom';
=======
import React from 'react';
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import { withRouter } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
  toolbar: {
    borderBottom: `1px solid ${theme.palette.divider}`,
  },
  toolbarTitle: {
    flex: 1,
  },
  toolbarSecondary: {
    justifyContent: 'space-between',
    overflowX: 'auto',
  },
  toolbarLink: {
    padding: theme.spacing(1),
    flexShrink: 0,
  },
}));

<<<<<<< HEAD
const notLoggedInPaths = ['/login', '/signup'];
const loggedInPaths = ['/profile'];

const Header = ({ sections, title, loggedIn }) => {
  const location = useLocation();
  const classes = useStyles();
  const isVisible = !(notLoggedInPaths.includes(location.pathname));

  return (isVisible &&
    <>
=======
const Header = (props) => {
  const classes = useStyles();
  const { sections, title, location } = props;

  const isVisible = location.pathname !== '/login';

  return ( isVisible &&
    <React.Fragment>
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
      <Toolbar className={classes.toolbar}>
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
      </Toolbar>
      <Toolbar component="nav" variant="dense" className={classes.toolbarSecondary}>
<<<<<<< HEAD
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
=======
        {sections.map((section) => (
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
    </React.Fragment>
>>>>>>> d74778c977703d835693f41c376bc3caa59fee2a
  );
}

Header.propTypes = {
  sections: PropTypes.array,
  title: PropTypes.string,
};

export default withRouter(Header); // to have access to location prop