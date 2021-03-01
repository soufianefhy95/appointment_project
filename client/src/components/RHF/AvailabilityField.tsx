import {
  Button,
  List,
  ListItem,
  makeStyles,
  Typography,
} from '@material-ui/core';
import { Availability } from 'store/types';
import { useMemo } from 'react';
import { Controller, useFormContext } from 'react-hook-form';
// import { useSelector } from 'react-redux';
// import { availabilitiesSelectors } from 'store/selectors';
import { formatDate, formatTimeRange } from 'utils/format';
import availabilitiesDefaultValues from 'mocks/availabilities.json';

type Props = {
  name: string;
};

const useStyles = makeStyles({
  list: {
    display: 'flex',
  },
  button: {
    background: '#7bc8c3',
    '&:focus': {
      background: '#7bc8c3',
    },
  },
  contained: {
    background: '#7bc8c3',
  },
});

const groupAvailabilitiesByDate = (availabilities: Availability[]) =>
  availabilities.reduce((result, availability) => {
    const formatedDate = formatDate(availability.startDate);
    if (result[formatedDate]) {
      return {
        ...result,
        [formatedDate]: [...result[formatedDate], availability],
      };
    }
    return { ...result, [formatedDate]: [availability] };
  }, {});

const AvailabilityField = (props: Props) => {
  const { name } = props;
  const classes = useStyles(props);
  const { control, errors } = useFormContext();
  // const availabilities = useSelector(availabilitiesSelectors.selectAll);
  const availabilities = availabilitiesDefaultValues;
  // const loading = useSelector(availabilitiesSelectors.selectLoading);
  const loading = false;

  const availabilitiesGroupByDate = useMemo(
    () => groupAvailabilitiesByDate(availabilities),
    [availabilities],
  );

  if (!availabilities.length) return null;
  return (
    <Controller
      control={control}
      defaultValue=""
      name={name}
      rules={{ required: 'The availability field is required' }}
      render={({ onChange, value }) => (
        <>
          <List className={classes.list}>
            {Object.keys(availabilitiesGroupByDate).map(
              (dayOfAvailabilities) => {
                const sortedAvailabilities =
                  availabilitiesGroupByDate[dayOfAvailabilities];
                return (
                  <div key={dayOfAvailabilities}>
                    <h2>{dayOfAvailabilities}</h2>
                    {sortedAvailabilities.map(({ id, startDate, endDate }) => {
                      const isSelected = value === id;
                      return (
                        <ListItem key={id}>
                          <Button
                            variant={isSelected ? 'contained' : 'outlined'}
                            color="primary"
                            disabled={loading}
                            onClick={() => {
                              onChange(isSelected ? '' : id);
                            }}
                            classes={{
                              root: isSelected && classes.button,
                              contained: classes.contained,
                            }}
                          >
                            <Typography
                              color={!isSelected ? 'textPrimary' : 'inherit'}
                            >
                              {formatTimeRange({
                                from: startDate,
                                to: endDate,
                              })}
                            </Typography>
                          </Button>
                        </ListItem>
                      );
                    })}
                  </div>
                );
              },
            )}
          </List>
          {errors[name] ? (
            <Typography color="error">{errors[name].message}</Typography>
          ) : null}
        </>
      )}
    />
  );
};

export default AvailabilityField;
