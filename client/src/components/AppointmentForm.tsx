import { Button, makeStyles } from '@material-ui/core';
import { useCallback, useEffect } from 'react';
import { FormProvider, useForm } from 'react-hook-form';
import {
  useDispatch,
  // useSelector,
} from 'react-redux';
import {
  appointmentsActions,
  availabilitiesActions,
  patientsActions,
  practitionersActions,
} from 'store/actions';
// import {
//   availabilitiesSelectors,
//   patientsSelectors,
//   practitionersSelectors,
// } from 'store/selectors';
import AvailabilityField from './RHF/AvailabilityField';
import SelectField, { getOptionsDefault } from './RHF/SelectField';
import practitionersDefaultValues from 'mocks/practitioners.json';
import patientsDefaultValues from 'mocks/patients.json';
import availabilitiesDefaultValues from 'mocks/availabilities.json';

type AppointmentFormValues = {
  practitionerId: number;
  patientId: number;
  availabilityId: number;
};

const useStyles = makeStyles({
  button: {
    background: '#7bc8c3',
    color: 'white',
    marginTop: '16px',
  },
  select: {
    padding: '8px',
    margin: '16px',
    border: 'none',
    borderBottom: '1px solid #333',
    cursor: 'pointer',
  },
});

const AppointmentForm = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const methods = useForm<AppointmentFormValues>();
  const { handleSubmit, setValue, reset } = methods;

  // const practitioners = useSelector(practitionersSelectors.selectAll);
  // const patients = useSelector(patientsSelectors.selectAll);
  // const availabilities = useSelector(availabilitiesSelectors.selectEntities);
  const practitioners = practitionersDefaultValues;
  const patients = patientsDefaultValues;
  const availabilities = availabilitiesDefaultValues;

  useEffect(() => {
    dispatch(practitionersActions.getList());
    dispatch(patientsActions.getList());
  }, []);

  const onPractitionerChange = useCallback(
    (practitionerId: number) => {
      setValue('availabilityId', '');
      if (practitionerId) {
        dispatch(
          availabilitiesActions.getList({
            params: {
              practitionerId,
            },
          }),
        );
      } else {
        dispatch(availabilitiesActions.reset());
      }
    },
    [setValue, dispatch],
  );

  const onSubmit = useCallback(
    ({ practitionerId, patientId, availabilityId }: AppointmentFormValues) => {
      const { startDate, endDate } = availabilities[availabilityId];
      const item = { practitionerId, patientId, startDate, endDate };
      dispatch(appointmentsActions.create({ item }));
      reset();
    },
    [availabilities],
  );

  return (
    <FormProvider {...methods}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <SelectField
          label="Practitioner"
          name="practitionerId"
          options={getOptionsDefault(practitioners)}
          placeholder="Select a practitioner"
          required="The practitioner field is required"
          onChange={onPractitionerChange}
          className={classes.select}
        />
        <SelectField
          label="Patient"
          name="patientId"
          options={getOptionsDefault(patients)}
          placeholder="Select a patient"
          required="The patient field is required"
          className={classes.select}
        />
        <AvailabilityField name="availabilityId" />
        <Button
          type="submit"
          variant="contained"
          classes={{ root: classes.button }}
        >
          Create appointment
        </Button>
      </form>
    </FormProvider>
  );
};

export default AppointmentForm;
