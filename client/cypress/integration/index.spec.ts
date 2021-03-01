describe('Can see the technical test intro', () => {
  it('can visit the home page', () => {
    cy.visit('/');
    cy.pick('intro').should('be.visible');
  });
});
