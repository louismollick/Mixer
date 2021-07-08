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
    },
    label: {
        color: "black"
    }
}));

const StrengthTypeRadio = ({ strengthType, setStrengthType }) => {
    const data = ["WEAK", "MEDIUM", "STRONG"];
    const classes = useStyles();

    return (
        <FormControl component="fieldset" className={classes.root}>
            <FormLabel component="legend" className={classes.label} >Strength Type</FormLabel>
            <RadioGroup row aria-label="strengthType" name="strengthType" value={strengthType} onChange={(e) => setStrengthType(e.target.value)}>
                {data.map(el => <FormControlLabel value={el} key={el} control={<Radio />} label={el} />)}
            </RadioGroup>
        </FormControl>
    )
}

export default StrengthTypeRadio;
