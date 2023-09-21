describe('TC-PBI20.1-2-1-VALIDATE-USER-LENGTH\n Senario : create an user: username length >45' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should open add form, type user data but the username length should be 45',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('00000-0001-0000-0002-0000-0003-0000-0004-23456')
                               .should('have.value','00000-0001-0000-0002-0000-0003-0000-0004-2345')
        cy.get('.ann-password').type("Sasadmin22*")
        cy.get('.ann-confirm-password').type("Sasadmin22*")
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
    })
});