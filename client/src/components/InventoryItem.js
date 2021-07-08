import React from 'react'
import Card from '@material-ui/core/Card';

import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core';
import CardActions from '@material-ui/core/CardActions';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';


const useStyles = makeStyles({
  root: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around", 
    width: 150,
    height: 150,
  },
  title: {
    fontSize: 18,
    textAlign: "center",
  },
  actions: {
    justifyContent: "center",
    alignItems: "center"
  }
});

const InventoryItem = ({ item, deleteItem }) => {
  const classes = useStyles();

  return (
    <Card className={classes.root}>
      <CardContent>
        <Typography className={classes.title} color="textSecondary" >
          {item.name}
        </Typography>
      </CardContent>
      <CardActions className={classes.actions}>
        <Button size="small" onClick={() => deleteItem(item)}><DeleteIcon /></Button>
      </CardActions>
    </Card>
  );
}

export default InventoryItem
