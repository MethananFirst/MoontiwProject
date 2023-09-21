describe('TC-PBI20.1-3-6-VALIDATE-USER-LENGTH\n Senario : update vichchuda - email is <three spaces>' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have the username vichchuda in the user listing page',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('vichchuda').parent('.ann-item')
    })

    it('should have an edit button and click the edit button',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('vichchuda').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()
    })

    it('should have pre-filled data in form.',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('vichchuda').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-username').should('have.value','vichchuda')
        cy.get('.ann-name').should('have.value','Vichchuda Tedoloh')
        cy.get('.ann-email').should('have.value','vichchuda.ted@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','announcer')
    })

    it('should clear value in email and click submit',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('vichchuda').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-email').clear().type('   ')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should show validation message : [Email] Please fill out this field.',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('vichchuda').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-email').clear().type('   ')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()

        cy.get('input:invalid').should('have.length',1)
        cy.get('.ann-email').then(($input)=>{
            expect($input[0].validationMessage).to.eq('Please fill out this field.')
        })
    })

});