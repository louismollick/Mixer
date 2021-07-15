import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
const useStyles = makeStyles((theme) => ({
  sectionTitle: {
    marginTop: '0.5em',
  }
}));

const IngredientList = ({ ingredientType, missingIngredients, allIngredients }) => {
  const classes = useStyles();

  return (<>
    <Typography className={classes.sectionTitle} color="textSecondary" variant="overline" component="h6">
      {ingredientType}
    </Typography>
    <Typography color="textSecondary" variant="overline" component="div">
      <Box component="ul" marginY="0">
        {missingIngredients?.map(el =>
          <li>
            {el.name}
            <Typography color="error" variant="overline" component="span">
              {" (MISSING)"}
            </Typography>
          </li>
        )}
        {allIngredients.filter(el => !missingIngredients?.some(mis => el.name === mis.name)).map(el => <li>{el.name}</li>)}
      </Box>
    </Typography>
  </>
  )
}

export default IngredientList
