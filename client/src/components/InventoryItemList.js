import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import InventoryItem from './InventoryItem';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    padding: '3rem',
  }
}));

const InventoryItemList = ({ categoryName, itemList }) => {
  const classes = useStyles();

  if (!itemList)
    return <></>;

  return (
    <div className={classes.root}>
      <Typography variant="h5" gutterBottom>
        {categoryName}
      </Typography>
      {(() => {
        if (itemList.length > 0)
          return <Grid container spacing={10}>
            {itemList.map(invItem => <Grid item xs={1} key={invItem.name}>
              <InventoryItem item={invItem} />
            </Grid>)}
          </Grid>;
        else return <Typography>
          {`No ${categoryName.toLowerCase()} in inventory.`}
        </Typography>;
      })()}

    </div>
  );
}

export default InventoryItemList
