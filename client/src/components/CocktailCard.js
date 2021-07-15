import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import clsx from 'clsx';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CardActions from '@material-ui/core/CardActions';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Collapse from '@material-ui/core/Collapse';

import IngredientList from '../components/IngredientList'

const useStyles = makeStyles((theme) => ({
  makeStatus: {
    position: 'absolute',
    bottom: 0,
    paddingLeft: '0.5em',
    paddingRight: '0.5em',
    lineHeight: '2em',
    borderTopRightRadius: '1em',
  },
  canMake: {
    backgroundColor: 'rgb(60,179,113)'
  },
  missingIngredients: {
    backgroundColor: 'rgb(210, 43, 43)'
  },
  cocktailName: {
    fontSize: '1.2em'
  },
  expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  },
  expandOpen: {
    transform: 'rotate(180deg)',
  },
  content: {
    paddingBottom: 0
  },
  actions: {
    paddingLeft: '1em'
  },
  collapse: {
    paddingTop: 0,
  }
}));

const CocktailCard = ({ cocktail, missingAlcohols, missingModifiers }) => {
  const [expanded, setExpanded] = useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const classes = useStyles();

  const canMake = !(missingAlcohols?.length > 0 || missingModifiers?.length > 0);

  return (
    <Card>
      <Box position="relative">
        <CardMedia
          component="img"
          alt={cocktail.name}
          height="280"
          image={cocktail.imgUrl}
          title={cocktail.name}
        />
        <Typography variant="overline"
          className={clsx(classes.makeStatus,
            {
              [classes.canMake]: canMake,
              [classes.missingIngredients]: !canMake
            })} >
          {canMake ? "YOU CAN MAKE THIS" : "MISSING INGREDIENTS"}
        </Typography>
      </Box>
      <CardContent className={classes.content}>
        <Box display="flex" alignItems="center">
          <Box marginRight="auto">
            <Typography gutterBottom className={classes.cocktailName}>
              {cocktail.name}
            </Typography>
          </Box>
          <Box display="flex" flexDirection="column">
            <Typography gutterBottom color="textSecondary" variant="overline">
              TASTE:
            </Typography>
            <Typography gutterBottom color="textSecondary" variant="overline">
              SERVING:
            </Typography>
          </Box>
          <Box marginLeft="1em" display="flex" flexDirection="column">
            <Typography gutterBottom color="textSecondary" variant="overline" align="right">
              {cocktail.tasteTypes.join(', ')}
            </Typography>
            <Typography gutterBottom color="textSecondary" variant="overline" align="right">
              {cocktail.servingSize}
            </Typography>
          </Box>
        </Box>
        <Divider variant="middle" />
      </CardContent>
      <CardActions className={classes.actions} disableSpacing>
        <Typography gutterBottom marginLeft="auto" color="textSecondary" variant="overline" >
          INGREDIENTS
        </Typography>
        <IconButton
          className={clsx(classes.expand, {
            [classes.expandOpen]: expanded,
          })}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </IconButton>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent className={classes.collapse}>
          <IngredientList ingredientType="Alcohol" missingIngredients={missingAlcohols} allIngredients={cocktail?.alcohols} />
          <IngredientList ingredientType="Modifiers" missingIngredients={missingModifiers} allIngredients={cocktail?.modifiers} />
        </CardContent>
      </Collapse>
    </Card>
  )
}

export default CocktailCard;
