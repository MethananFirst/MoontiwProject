describe('TC-PBI20.1-1-5-VALIDATE-USER-LENGTH\n Senario : create an user: email is null' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should open add form, type user data but leave username blank and submit the form',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type("Sasadmin22*")
        cy.get('.ann-confirm-password').type("Sasadmin22*")
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('   ')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should show validation message : [Email] Please fill out this field.',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type("Sasadmin22*")
        cy.get('.ann-confirm-password').type("Sasadmin22*")
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('   ')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('input:invalid').should('have.length',1)
        cy.get('.ann-email').then(($input)=>{
            expect($input[0].validationMessage).to.eq('Please fill out this field.')
        })
    })

});