import { Box, Button } from '@material-ui/core';
import Typography from '@material-ui/core/Typography';
import axios from 'axios';
import React, { useEffect, useState, useCallback } from 'react'
import { Link } from 'react-router-dom';
import InventoryItemList from './InventoryItemList';
import CircularProgress from '@material-ui/core/CircularProgress';

const Inventory = ({ loggedIn, alcohols, modifiers, setAlcohols, setModifiers }) => {
  const [allAlcohols, setAllAlcohols] = useState(null);
  const [allModifiers, setAllModifiers] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  const addItem = (itemType, inventoryList, setInventoryList) => (item) => {
    const userId = window.localStorage.getItem('userId');
    const token = window.localStorage.getItem('token');
    return axios.put(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/${itemType}/${encodeURIComponent(item?.name)}`, null, {
      headers: {
        'Authorization': `${token}`
      }
    }).then(res => {
      if (!item)
        throw new Error(`Item name was not in the ${itemType} list.`)
      setInventoryList([...inventoryList, item]);
    }).catch(err => {
      console.log(err);
      setLoading(false);
      setError(true);
    })
  };

  const deleteItem = (itemType, inventoryList, setInventoryList) => (item) => {
    const userId = window.localStorage.getItem('userId');
    const token = window.localStorage.getItem('token');
    return axios.delete(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/${itemType}/${encodeURIComponent(item?.name)}`, {
      headers: {
        'Authorization': `${token}`
      }
    }).then(res => {
      const newInventory = inventoryList.filter(el => el.name !== item.name);
      setInventoryList(newInventory);
    }).catch(err => {
      console.log(err);
      setLoading(false);
      setError(true);
    })
  };

  const requestInventory = useCallback((userId, token) => {
    const reqUserAlcohol = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/alcohol`, {
      headers: {
        'Authorization': `${token}`
      }
    })
      .then(res => setAlcohols(res.data))
      .catch(err => {
        throw err
      });
    const reqUserModifier = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/modifier`, {
      headers: {
        'Authorization': `${token}`
      }
    })
      .then(res => setModifiers(res.data))
      .catch(err => {
        throw err
      });
    const reqAllAlcohol = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/alcohol`, {
      headers: {
        'Authorization': `${token}`
      }
    })
      .then(res => setAllAlcohols(res.data))
      .catch(err => {
        throw err
      });
    const reqAllModifiers = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/modifier`, {
      headers: {
        'Authorization': `${token}`
      }
    })
      .then(res => setAllModifiers(res.data))
      .catch(err => {
        throw err
      });

    return [reqAllAlcohol, reqAllModifiers, reqUserAlcohol, reqUserModifier];
  }, [setAlcohols, setModifiers]);

  useEffect(() => {
    if (!loggedIn)
      return setLoading(false); // return void

    try {
      const userId = window.localStorage.getItem('userId');
      const token = window.localStorage.getItem('token');
      Promise.all(requestInventory(userId, token))
        .then(data => setLoading(false))
    } catch (err) {
      setLoading(false);
      setError(true);
    }
  }, [loggedIn, requestInventory]);


  return (
    <div>
      {(() => {
        if (!loggedIn)
          return (
            <>
              <Typography variant="h5" align="center">
                Please login to view your inventory
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

        return <>
          <InventoryItemList categoryName={"Alcohols"} itemList={alcohols} fullItemList={allAlcohols}
            addItem={addItem("alcohol", alcohols, setAlcohols)} deleteItem ={deleteItem("alcohol", alcohols, setAlcohols)}/>
          <InventoryItemList categoryName={"Modifiers"} itemList={modifiers} fullItemList={allModifiers}
            addItem={addItem("modifier", modifiers, setModifiers)} deleteItem ={deleteItem("modifier", modifiers, setModifiers)}/>
        </>
      })()}
    </div>
  );
}

export default Inventory;
