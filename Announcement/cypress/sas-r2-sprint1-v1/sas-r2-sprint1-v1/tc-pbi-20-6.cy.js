describe('TC-PBI20-EDIT-USER-NO-AUTHORIZATION-CONTROL-6\n Senario : normal - no update', () => {

    let prevUpdatedOn
      
    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
    })
    
    
    it('should have username "jaruwan_w".',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item')
    })

    it('should have "edit" button.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()
    })

    it('should have pre-filled data in form.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.wait(200)
        cy.get('.ann-username').should('have.value','jaruwan_w')
        cy.get('.ann-name').should('have.value','Jaruwan Maneesart')
        cy.get('.ann-email').should('have.value','jaruwan.wee@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','announcer')
        cy.get('.ann-updated-on').then((updateOn)=>{
            prevUpdatedOn = updateOn.text()
        })

    })

    it('should add whitespace in username and name.\n should enable submit button',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.wait(200)
        cy.get('.ann-username').clear().type(' jaruwan_w ')
        cy.get('.ann-name').clear().type(' Jaruwan Maneesart ')
        cy.get('.ann-email').clear().type('jaruwan.wee@kmutt.ac.th')

        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').as('ann-submit')
        cy.get('@ann-submit').should('be.enabled')
        cy.get('@ann-submit').should('exist').click()
        cy.wait(200)
    })

    it('should show createdOn and updatedOn without any changed in user list view.',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('jaruwan_w').parent('.ann-item').as('ann-1')

        cy.wait(200) ;
        cy.get('@ann-1').contains('.ann-username','jaruwan_w')
        cy.get('@ann-1').contains('.ann-name','Jaruwan Maneesart')
        cy.get('@ann-1').contains('.ann-email','jaruwan.wee@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','announcer')
        cy.get('@ann-1').contains('.ann-updated-on',prevUpdatedOn)
    })

})