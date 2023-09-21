describe('TC-PBI17-VIEW-USER-DETAIL-NO-AUTHORIZATION-CONTROL-1', () => {
      
    it('should visit /admin/user',()=>{
      cy.visit('/admin/user')

      cy.wait(200)
    }) 

    it('First user should be username : "sanit".',()=>{
      cy.visit('/admin/user')

      cy.wait(200) ;
      cy.get('.ann-item').first().as('ann-1')
      cy.get('@ann-1').contains('.ann-username','sanit')
    }) 

    it('should have "edit" button.',()=>{
      cy.visit('/admin/user')
      
      cy.wait(200) ;
      cy.get('.ann-item').first().as('ann-1')
      cy.get('@ann-1').contains('.ann-username','sanit')
      cy.get('@ann-1').contains('.ann-button','edit').click()
    })

    it('The username "sanit" should have url "/admin/user/1".',()=>{
        cy.visit('/admin/user')
        
        cy.wait(200) ;
        cy.get('.ann-item').first().as('ann-1')
        cy.get('@ann-1').contains('.ann-username','sanit')
        cy.get('@ann-1').contains('.ann-button','edit').click()
        cy.wait(200) ;
        cy.url().should('contain','/admin/user/1/edit')
      })

      it('Show the detail of user "sanit".".',()=>{
        cy.visit('/admin/user/1/edit')
        
        cy.wait(200) ;
        cy.get('.ann-username').should('have.value','sanit')
        cy.get('.ann-name').should('have.value','Sanit Sirisawatvatana')
        cy.get('.ann-email').should('have.value','sanit.sir@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','admin')
        cy.get('.ann-created-on').contains('15 Aug 2023')
        cy.get('.ann-created-on').contains('08:00')
        cy.get('.ann-updated-on').contains('15 Aug 2023')
        cy.get('.ann-updated-on').contains('08:00')
      })
});