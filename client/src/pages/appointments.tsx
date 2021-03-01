import AppointmentForm from 'components/AppointmentForm';
import AppointmentList from 'components/AppointmentList';
import Section from 'components/Section';
import AllTasks from 'components/AllTasks';
import { makeStyles } from '@material-ui/core';

import * as osef from 'image/withoutAppointment.png';

const useStyles = makeStyles({
  instructions: {
    marginBottom: '20px',
  },
  goals: {
    marginBottom: '30px',
  },
  structurePage: {
    display: 'flex',
    justifyContent: 'space-around',
    marginBottom: '30px',
  },
});

const AppointmentsPage = () => {
  const classes = useStyles();
  return (
    <>
      <h1>Appointments</h1>
      <Section
        name="instructions"
        title="Instructions"
        className={classes.instructions}
      >
        <p>
          To book an appointment, we have to set the following required
          informations: the practitioner, the patient and date.
        </p>
        <p>The front-end implementation is already done.</p>
        <p>In first you have to genrate all availabilities.</p>
        <p>
          In the second time, you will create an end-point top create an
          appointment.
        </p>
        <p>
          We expect you to implement bonus features: add DTO pattern and add
          unit tests if needed
        </p>
      </Section>
      <AllTasks className={classes.goals} />
      <div className={classes.structurePage}>
        <Section name="appointment-form" title="Appointment Form">
          <AppointmentForm />
        </Section>
        <Section name="appointment-list" title="Appointment List">
          <AppointmentList />
        </Section>
      </div>
    </>
  );
};

AppointmentsPage.pageTitle = 'Appointments';
AppointmentsPage.pageSubtitle = "Let's get to work 👩‍💻";

export default AppointmentsPage;
