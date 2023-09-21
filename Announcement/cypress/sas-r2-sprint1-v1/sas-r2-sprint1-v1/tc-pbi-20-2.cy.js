describe('TC-PBI20-EDIT-USER-NO-AUTHORIZATION-CONTROL-2\n Senario : normal - update only username', () => {
      
    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
    })
    
    
    it('should have username "jaruwan.man".',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan.man').parent('.ann-item')
    })

    it('should have "edit" button.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan.man').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()
    })

    it('should have pre-filled data in form.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan.man').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.wait(200)
        cy.get('.ann-username').should('have.value','jaruwan.man')
        cy.get('.ann-name').should('have.value','Mrs. Jaruwan Maneesart')
        cy.get('.ann-email').should('have.value','jaruwan.man@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','admin')
    })

    it('should update only username.\n should enable submit button',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan.man').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.wait(200)
        cy.get('.ann-username').clear().type('jaruwan_w')

        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').as('ann-submit')
        cy.get('@ann-submit').should('be.enabled')
        cy.get('@ann-submit').should('exist').click()
        cy.wait(200)
    })

    it('should show the updated data in user list view.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item').as('ann-1')

        cy.wait(200) ;
        cy.get('@ann-1').contains('.ann-username','jaruwan_w')
        cy.get('@ann-1').contains('.ann-name','Mrs. Jaruwan Maneesart')
        cy.get('@ann-1').contains('.ann-email','jaruwan.man@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','admin')
    })

})