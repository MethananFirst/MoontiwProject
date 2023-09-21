describe('TC-PBI20.3-1-2-VALIDATE-USER-EMAIL\n Senario : create an user: invalid email' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it("should have a validation message: [Email] Please enter a part following '@'.",()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('sunisa')
        cy.get('.ann-password').type("Sasadvisor22*")
        cy.get('.ann-confirm-password').type("Sasadvisor22*")
        cy.get('.ann-name').type('Sunisa Sathapornvajana')
        cy.get('.ann-email').type('sunisa.sat@')
        cy.get('.ann-role').select('announcer')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('input:invalid').should('have.length',1)
        cy.get('.ann-email').then(($input)=>{
            expect($input[0].validationMessage).to.contains("Please enter a part following '@'.")
        })
    })
});