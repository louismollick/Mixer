import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: 675,
        paddingBottom: "1.25rem",
    },
    labelRoot: {
        color: "black",
        fontSize: "1rem",
    },
}));

const TasteTypesSelect = ({ tasteTypes, setTasteTypes }) => {
    const data = ["SWEET", "SOUR", "BITTER", "SPICY", "MINTY", "FRUITY"]
    const classes = useStyles();

    return (
        <Autocomplete
            multiple
            id="taste-types"
            className={classes.root}
            options={data.filter(taste => !tasteTypes.some(selected => selected === taste)) || []}
            defaultValue={[]}
            onChange={(event, value) => setTasteTypes(value)}
            renderInput={(params) => (
                <TextField
                    {...params}
                    InputLabelProps={{
                        classes: {
                            root: classes.labelRoot,
                        }
                    }}
                    variant="standard"
                    label="Taste Types"
                    placeholder="Enter here..."
                />
            )}
        />
    )
}

export default TasteTypesSelect;
