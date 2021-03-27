import { Box, Button } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import InventoryItemList from '../components/InventoryItemList';
import CircularProgress from '@material-ui/core/CircularProgress';

const Inventory = ({ loggedIn }) => {
  const [alcohols, setAlcohols] = useState(null);
  const [modifier, setModifiers] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    if (!loggedIn) {
      setLoading(false);
      return;
    }

    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');
    if (!userId || !token) {
      setLoading(false);
      setError(true);
      return;
    }
    try {
      const reqAlcohol = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/alcohol`, {
        headers: {
          'Authorization': `${token}`
        }
      })
        .then(res => {
          console.log(res.data)
          setAlcohols(res.data);
        })
        .catch(err => {
          throw err
        });
      const reqModifier = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/modifier`, {
        headers: {
          'Authorization': `${token}`
        }
      })
        .then(res => {
          console.log(res.data)
          setModifiers(res.data);
        })
        .catch(err => {
          throw err
        });

      Promise.all([reqAlcohol, reqModifier]).then(data => {
        setLoading(false);
      })
    } catch (error) {
      setLoading(false);
      setError(true);
    }

  }, [loggedIn])

  return (
    <div>
      <Typography variant="h5" align="center">
        Inventory
      </Typography>
      {(() => {
        if (!loggedIn)
          return (
            <>
              <Typography variant="h5" align="center">
                Please login to view your inventory
              </Typography>
              <Box display="flex" alignItems="center" justifyContent="center" >
                <Button variant="contained" color="primary" component={Link} to={'/login'}>Login</Button>
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

        return <>
          <InventoryItemList categoryName={"Alcohols"} itemList={alcohols} />
          <InventoryItemList categoryName={"Modifiers"} itemList={modifier} />
        </>
      })()}
    </div>
  );
}

export default Inventory;
