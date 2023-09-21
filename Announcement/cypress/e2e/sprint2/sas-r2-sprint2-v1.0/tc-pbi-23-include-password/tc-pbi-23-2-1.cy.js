describe('TC-PBI23-2-1-INCLUDE-PASSWORD\n Senario : create an user: password does not match' , () => {

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

    it('should open add form, type user data but password does not match with confirm password and submit the form',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type('Sasowner22#')
        cy.get('.ann-confirm-password').type('Sasowner22*')
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
    })

    it('should show validation message : [Password] 8-14,1(Ulns)',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type('Sasowner22#')
        cy.get('.ann-confirm-password').type('Sasowner22*')
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('.ann-error-password').contains('The password DOES NOT match')
    })
});