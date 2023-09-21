describe('TC-PBI24-1-MATCH-PASSWORD\n Senario : normal - correct password' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user/match')
    }) 
      

    it('should have input[type=password] for field password',()=>{
        cy.visit('/admin/user/match')

        cy.get('.ann-password').invoke('attr','type').should('eq','password')
    })

    it('should type username and password',()=>{
        cy.visit('/admin/user/match')

        cy.get('.ann-username').type('sanit')
        cy.get('.ann-password').type('Sasadmin22*')
    })

    it('should show message : Password Matched',()=>{
        cy.visit('/admin/user/match')

        cy.get('.ann-username').type('sanit')
        cy.get('.ann-password').type('Sasadmin22*')
        cy.get('.ann-button').contains(/match/g,{matchCase: false}).should('exist').click()

        cy.get('.ann-message').contains('Password Matched')
    })
});