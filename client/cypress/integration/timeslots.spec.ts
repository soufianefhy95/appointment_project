describe('Can see the time slot list', () => {
  it('can visit the time slot page', () => {
    cy.visit('/timeslots');
    cy.pick('timeslot-list').should('be.visible');
  });
});
