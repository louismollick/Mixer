import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import InventoryItem from './InventoryItem';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  }
}));

const InventoryItemList = ({ categoryName, itemList, fullItemList, addItem, deleteItem }) => {
  const [selectedItem, setSelectedItem] = useState(null);
  const classes = useStyles();

  if (!itemList)
    return <></>;

  return (
    <div className={classes.root}>
      <Typography fontSize="1rem" gutterBottom>
        {categoryName}
      </Typography>
      {(() => {
        if (itemList.length > 0)
          return <Grid container spacing={1}>
            {itemList.map(invItem => <Grid item key={invItem.name}>
              <InventoryItem item={invItem} deleteItem={deleteItem} />
            </Grid>)}
          </Grid>;
        else return <Typography>
          {`No ${categoryName.toLowerCase()} in inventory.`}
        </Typography>;
      })()}
      <Box display="flex" padding="1rem">
        <Autocomplete
          id={`item-select-${categoryName.toLowerCase()}`}
          value={selectedItem}
          options={fullItemList?.filter(item => !itemList.some(inv => inv.name === item.name)) || []}
          getOptionLabel={(option) => option.name}
          getOptionSelected={(option, value) => option.name === value.name}
          onChange={(event, value) => {
            setSelectedItem(value);
          }}
          style={{ width: 300 }}
          renderInput={(params) =>
            <TextField {...params} label={`Select ${categoryName} to add...`} variant="outlined" />
          }
        />
        <Button variant="contained" color="primary" disabled={selectedItem === null}
          onClick={() => {
            addItem(selectedItem);
            setSelectedItem(null);
          }}>ADD</Button>
      </Box>
    </div>
  );
}

export default InventoryItemList;
