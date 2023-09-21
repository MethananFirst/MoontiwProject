describe('TC-PBI16-VIEW-USER-ALL-NO-AUTHORIZATION-CONTROL-1', () => {
    beforeEach(()=> cy.visit('/admin/user')) ;

    it('Users should have 4 rows', () => {
      cy.visit("/admin/user")
      cy.wait(200);
      cy.get('.ann-item').should('have.length', 4)
    })

    it('First user should be username : "sanit"',()=>{
        cy.get('.ann-item').first().as('ann-1')
        cy.get('@ann-1').contains('.ann-username','sanit')
        cy.get('@ann-1').contains('.ann-name','Sanit')
        cy.get('@ann-1').contains('.ann-name','Sirisawatvatana')
        cy.get('@ann-1').contains('.ann-email','sanit.sir@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','admin')
        cy.get('@ann-1').contains('.ann-created-on','15 Aug 2023')
        cy.get('@ann-1').contains('.ann-created-on','08:00')
        cy.get('@ann-1').contains('.ann-updated-on','15 Aug 2023')
        cy.get('@ann-1').contains('.ann-updated-on','08:00')
      })
  
      it('Last user should be username : "vichchuda"',()=>{
        cy.get('.ann-item').last().as('ann-1')
        cy.get('@ann-1').contains('.ann-username','vichchuda')
        cy.get('@ann-1').contains('.ann-name','Vichchuda')
        cy.get('@ann-1').contains('.ann-name','Tedoloh')
        cy.get('@ann-1').contains('.ann-email','vichchuda.ted@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','announcer')
        cy.get('@ann-1').contains('.ann-created-on','16 Aug 2023')
        cy.get('@ann-1').contains('.ann-created-on','09:30')
        cy.get('@ann-1').contains('.ann-updated-on','16 Aug 2023')
        cy.get('@ann-1').contains('.ann-updated-on','09:30')
      }) ;
  })