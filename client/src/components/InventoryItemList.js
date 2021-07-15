import React, { useState, useCallback, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import InventoryItem from './InventoryItem';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import CircularProgress from '@material-ui/core/CircularProgress';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBeer, faLemon } from '@fortawesome/free-solid-svg-icons';

import axios from 'axios';

const useStyles = makeStyles((theme) => ({
  itemSearch: {
    display: 'flex',
    margin: theme.spacing(3, 0)
  },
  icon: {
    paddingRight: '0.5rem',
    alignSelf: 'center'
  }
}));

const InventoryItemList = ({ itemType }) => {
  const [UserItems, setUserItems] = useState(null);
  const [AllItems, setAllItems] = useState(null);
  const [SelectedItem, setSelectedItem] = useState(null);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const classes = useStyles();

  const requestInventory = useCallback((userId, token, cancelToken) => {
    const reqUserItems = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/${itemType}`, {
      headers: {
        'Authorization': `${token}`
      },
      cancelToken
    })
      .then(res => setUserItems(res.data))
      .catch(err => {
        throw err
      });
    const reqAllItems = axios.get(`${process.env.REACT_APP_BACKEND_URL}/api/${itemType}`, {
      headers: {
        'Authorization': `${token}`
      },
      cancelToken
    })
      .then(res => setAllItems(res.data))
      .catch(err => {
        throw err
      });
    return [reqUserItems, reqAllItems];
  }, [itemType]);

  const addItem = (item) => {
    const userId = window.localStorage.getItem('userId');
    const token = window.localStorage.getItem('token');
    return axios.put(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/${itemType}/${encodeURIComponent(item?.name)}`, null, {
      headers: {
        'Authorization': `${token}`
      }
    }).then(res => {
      if (!item)
        throw new Error(`Item name was not in the ${itemType} list.`)
      setUserItems([...UserItems, item]);
    }).catch(err => {
      console.log(err);
      setLoading(false);
      setError(true);
    })
  };

  const deleteItem = (item) => {
    const userId = window.localStorage.getItem('userId');
    const token = window.localStorage.getItem('token');
    return axios.delete(`${process.env.REACT_APP_BACKEND_URL}/api/user/${userId}/${itemType}/${encodeURIComponent(item?.name)}`, {
      headers: {
        'Authorization': `${token}`
      }
    }).then(res => {
      const newInventory = UserItems.filter(el => el.name !== item.name);
      setUserItems(newInventory);
    }).catch(err => {
      console.log(err);
      setLoading(false);
      setError(true);
    })
  };

  useEffect(() => {
    const source = axios.CancelToken.source();
    try {
      const userId = window.localStorage.getItem('userId');
      const token = window.localStorage.getItem('token');
      const cancelToken = source.token;
      if (!userId || !token) return;
      Promise.all(requestInventory(userId, token, cancelToken))
        .then(data => setLoading(false))
    } catch (err) {
      if (axios.isCancel(err)) return;
      setLoading(false);
      setError(true);
    }

    return () => source.cancel(); // cancel requests on unmount
  }, [requestInventory]);

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
    <Box display="flex" flexDirection="column" width="100%">
      <div className={classes.itemSearch}>
        <FontAwesomeIcon className={classes.icon} icon={itemType === "alcohol" ? faBeer : faLemon} size="2x" />
        <Autocomplete
          id={`item-select-${itemType.toLowerCase()}`}
          value={SelectedItem}
          options={AllItems?.filter(item => !UserItems.some(inv => inv.name === item.name)) || []}
          getOptionLabel={(option) => option.name}
          getOptionSelected={(option, value) => option.name === value.name}
          onChange={(event, value) => {
            setSelectedItem(value);
          }}
          style={{ flexGrow: 1 }}
          renderInput={(params) =>
            <TextField {...params} label={`Add a ${itemType} to your inventory...`} variant="outlined" />
          }
        />
        <Button className={classes.addButton} variant="contained" color="primary" disabled={SelectedItem === null}
          onClick={() => {
            addItem(SelectedItem);
            setSelectedItem(null);
          }}>ADD</Button>
      </div>
      {(() => {
        if (UserItems?.length > 0)
          return <Grid container spacing={1}>
            {UserItems.map(invItem => <Grid item key={invItem.name}>
              <InventoryItem item={invItem} deleteItem={deleteItem} />
            </Grid>)}
          </Grid>;
        else return <Typography>
          {`No ${itemType.toLowerCase()} in inventory.`}
        </Typography>;
      })()}
    </Box>
  );
}

export default InventoryItemList;
