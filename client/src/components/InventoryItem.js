import React from 'react'
import Card from '@material-ui/core/Card';

import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core';

// import CardActions from '@material-ui/core/CardActions';
// import Button from '@material-ui/core/Button';

const useStyles = makeStyles({
  root: {
    minWidth: 100,

  },
  title: {
    fontSize: 18,
    textAlign: "center"
  }
});

const InventoryItem = ({ item }) => {
  const classes = useStyles();

  return (
    <Card className={classes.root}>
      <CardContent>
        <Typography className={classes.title} color="textSecondary" >
          {item.name}
        </Typography>
      </CardContent>
      {/* <CardActions>
        <Button size="small">Remove</Button>
      </CardActions> */}
    </Card>
  );
}

export default InventoryItem
