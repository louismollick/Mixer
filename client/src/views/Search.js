import React, { useEffect, useState } from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import axios from 'axios';

const Search = ({ searchOptions }) => {
  const [result, setResult] = useState([]);

  useEffect(() => {
    const token = window.localStorage.getItem('token');
    axios.post(`${process.env.REACT_APP_BACKEND_URL}/api/cocktail`, {
      cName: "",
      alcohol: searchOptions?.alcohols?.map(el => el.name),
      modifier: searchOptions?.modifiers?.map(el => el.name),
      tasteType: searchOptions.tasteTypes,
      strengthType: searchOptions.strengthType,
      servingSize: searchOptions.servingSize
    }, {
      headers: {
        'Authorization': `${token}`
      }
    })
      .then(res => setResult(res.data))
      .catch(err => {
        throw err
      });
  }, [searchOptions])

  return (
    <div>
      <Typography fontSize="1rem" gutterBottom>
        Search Results
      </Typography>
      {(() => {
        if (result.length > 0)
          return <Grid container spacing={1}>
            {result.map(cocktail => <Grid item key={cocktail.name}>
              <Card>
                <CardMedia
                  component="img"
                  alt={cocktail.name}
                  height="140"
                  image={cocktail.imgUrl}
                  title={cocktail.name}
                />
                <CardContent>
                  <Typography gutterBottom variant="h5" component="h2">
                    {cocktail.name}
                  </Typography>
                </CardContent>
                {/* <CardActions>
                  <Button size="small" onClick={() => deleteItem(item)}><DeleteIcon /></Button>
                </CardActions> */}
              </Card>
            </Grid>)}
          </Grid>;
        else return <Typography>
          {`No cocktails found.`}
        </Typography>;
      })()}
    </div>
  )
}

export default Search;
