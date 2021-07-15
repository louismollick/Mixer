import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import CircularProgress from '@material-ui/core/CircularProgress';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

import axios from 'axios';

import CocktailCard from '../components/CocktailCard'

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    maxWidth: '1140px',
    margin: 'auto'
  },
  canMake: {
    position: 'absolute',
    bottom: 0,
    paddingLeft: '1em',
    paddingRight: '1em',
    lineHeight: '2em',
    borderTopRightRadius: '1em',
    backgroundColor: 'red'
  },
  arrow: {
    marginRight: '0.25em'
  },
}));

const Search = ({ loggedIn }) => {
  const [Result, setResult] = useState(null);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const classes = useStyles();

  useEffect(() => {
    if (!loggedIn) return;
    const userId = window.localStorage.getItem('userId');
    const token = window.localStorage.getItem('token');

    const source = axios.CancelToken.source();
    axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/cocktailsWithInventory`, {
      headers: {
        'Authorization': `${token}`
      },
      cancelToken: source.token
    })
      .then(res => {
        console.log(res.data);
        console.log(res.data.cocktailsYouCanMake)
        setResult(res.data);
        setLoading(false);
      })
      .catch(err => {
        if (axios.isCancel(err)) return;
        setLoading(false);
        setError(true);
      });

    return () => source.cancel();
  }, [loggedIn]);

  if (!loggedIn)
    return (
      <>
        <Typography variant="h5" align="center">
          Please login to search for cocktails.
        </Typography>
        <Typography variant="body1" align="center">
          For testing purposes, you can use the email "user@example.com" with password "12345678" if you don't want to make an account.
        </Typography>
        <Box display="flex" alignItems="center" justifyContent="center" >
          <Button id="loginBtn" variant="contained" color="primary" component={Link} to={'/login'}>Login</Button>
        </Box>
      </>
    );

  if (error)
    return (
      <Typography variant="h5" align="center">
        Error. Something went wrong.
      </Typography>
    );
  if (loading)
    return (
      <Box display="flex" alignItems="center" justifyContent="center" >
        <CircularProgress />
      </Box>
    );

  return (
    <div className={classes.root}>
      <Grid container spacing={1}>
        <Grid item xs={12}>
          <Button variant="contained" color="primary" component={Link} to={'/'} fullWidth>
            <ArrowBackIcon className={classes.arrow} />
            Return to inventory
          </Button>
        </Grid>
        {(() => {
          return <>
            {Result?.cocktailsYouCanMake?.map(cocktail => <Grid item xs={12} sm={6} md={4} key={cocktail.name}>
              <CocktailCard cocktail={cocktail} />
            </Grid>)}
            {Result?.cocktailsMissingIngredients?.map(el => <Grid item xs={12} sm={6} md={4} key={el.cocktail.name} >
              <CocktailCard cocktail={el.cocktail} missingAlcohols={el.missingAlcohols} missingModifiers={el.missingModifiers} />
            </Grid>)}
          </>;
        })()}
      </Grid>
    </div >
  )
}

export default Search;
