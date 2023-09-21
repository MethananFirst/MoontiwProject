describe('TC-PBI20.1-4-1-VALIDATE-USER-LENGTH\n Senario : update pornthip: name length >100' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have the username pornthip in the user listing page',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('pornthip').parent('.ann-item')
    })

    it('should have an edit button and click the edit button',()=>{    
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('pornthip').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()
    })

    it('should have pre-filled data in form.',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('pornthip').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-username').should('have.value','pornthip')
        cy.get('.ann-name').should('have.value','Pornthip Sirijutikul')
        cy.get('.ann-email').should('have.value','pornthip.sri@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','announcer')
    })

    it('should type value in name and limit length to 100',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('pornthip').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-button','edit').should('exist').click()

        cy.get('.ann-name').clear().type('00000-0001-0000-0002-0000-0003-0000-0004-0000-0005-0000-0006-0000-0007-0000-0008-0000-0009-0000-0010-')
        .should('have.value','00000-0001-0000-0002-0000-0003-0000-0004-0000-0005-0000-0006-0000-0007-0000-0008-0000-0009-0000-0010')
    })

});