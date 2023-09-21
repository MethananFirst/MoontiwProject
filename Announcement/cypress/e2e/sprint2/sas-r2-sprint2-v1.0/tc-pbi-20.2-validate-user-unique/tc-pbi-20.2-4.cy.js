describe('TC-PBI20.2-4-VALIDATE-USER-LENGTH\n Senario : update sanit - duplicate username' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have the username sanit in the user listing page',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('sanit').parent('.ann-item')
    })

    it('should have an edit button and click the edit button',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('sanit').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()
    })

    it('should have pre-filled data in form.',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('sanit').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-username').should('have.value','sanit')
        cy.get('.ann-name').should('have.value','Sanit Sirisawatvatana')
        cy.get('.ann-email').should('have.value','sanit.sir@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','admin')
    })

    it('should clear value in username and click submit',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('sanit').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-username').clear().type('jaruwan_w')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should show validation message : [Username] does not unique',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('sanit').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-username').clear().type('jaruwan_w')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('.ann-error-username').contains('does not unique')
    })

});