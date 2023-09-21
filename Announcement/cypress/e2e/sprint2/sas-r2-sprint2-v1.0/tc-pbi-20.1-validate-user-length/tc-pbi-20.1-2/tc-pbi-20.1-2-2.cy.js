describe('TC-PBI20.1-2-2-VALIDATE-USER-LENGTH\n Senario : create an user: name length >100' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should open add form, type user data but the name length should be 100',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type("Sasadmin22*")
        cy.get('.ann-confirm-password').type("Sasadmin22*")
        cy.get('.ann-name').type('00000-0001-0000-0002-0000-0003-0000-0004-0000-0005-0000-0006-0000-0007-0000-0008-0000-0009-0000-0010-')
        .should('have.value','00000-0001-0000-0002-0000-0003-0000-0004-0000-0005-0000-0006-0000-0007-0000-0008-0000-0009-0000-0010')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
    })
});