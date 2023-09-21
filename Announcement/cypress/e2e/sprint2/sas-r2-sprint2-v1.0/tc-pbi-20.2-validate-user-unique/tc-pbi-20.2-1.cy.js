describe('TC-PBI20.2-1-VALIDATE-USER-UNIQUE\n Senario : create an user: duplicate username' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should open add form, type user data but the username is duplicated',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('sanit')
        cy.get('.ann-password').type("Sasadmin22*")
        cy.get('.ann-confirm-password').type("Sasadmin22*")
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('.ann-error-username').contains('does not unique')
    })
});