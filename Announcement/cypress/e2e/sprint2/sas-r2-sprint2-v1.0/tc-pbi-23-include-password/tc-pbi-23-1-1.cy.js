describe('TC-PBI23-1-1-INCLUDE-PASSWORD\n Senario : create an user: password is null' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should have input[type=password] for field password and confirm password',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-password').invoke('attr','type').should('eq','password')
        cy.get('.ann-confirm-password').invoke('attr','type').should('eq','password')
    })

    it('should open add form, type user data but leave password and comfirm password to blank and submit the form',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').clear()
        cy.get('.ann-confirm-password').clear()
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should show validation message : [Password] Please fill out this field.',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').clear()
        cy.get('.ann-confirm-password').clear()
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('input:invalid').should('have.length',2)
        cy.get('.ann-password').then(($input)=>{
            expect($input[0].validationMessage).to.eq('Please fill out this field.')
        })
    })

});