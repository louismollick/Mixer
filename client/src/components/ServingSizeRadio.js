import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';

const useStyles = makeStyles((theme) => ({
    root: {
        display: "block",
        paddingBottom: "1rem",
    }
}));

const ServingSizeRadio = ({ servingSize, setServingSize }) => {
    const data = ["INDIVIDUAL", "GROUP"];
    const classes = useStyles();

    return (
        <FormControl component="fieldset" className={classes.root}>
            <FormLabel component="legend" style={{ color: "black" }} >Serving Size</FormLabel>
            <RadioGroup row aria-label="servingSize" name="servingSize" value={servingSize} onChange={(e) => setServingSize(e.target.value)}>
                {data.map(el => <FormControlLabel value={el} key={el} control={<Radio />} label={el} />)}
            </RadioGroup>
        </FormControl>
    )
}

export default ServingSizeRadio;
