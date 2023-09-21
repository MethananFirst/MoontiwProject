describe('TC-PBI17-VIEW-USER-DETAIL-NO-AUTHORIZATION-CONTROL-2', () => {
      
    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
      }) 

    it('Third user should be username : "pornthip".',()=>{
      cy.visit('/admin/user')

      cy.wait(200) ;
      cy.get('.ann-item').eq(2).as('ann-2')
      cy.get('@ann-2').contains('.ann-username','pornthip')
    }) 

    it('should have "edit" button.',()=>{
      cy.visit('/admin/user')

      cy.wait(200) ;
      cy.get('.ann-item').eq(2).as('ann-2')
      cy.get('@ann-2').contains('.ann-username','pornthip')
      cy.get('@ann-2').contains('.ann-button','edit').click()
    })

    it('The username "pornthip" should have url "/admin/user/2/edit".',()=>{
        cy.visit('/admin/user')
        
        cy.get('.ann-item').eq(2).as('ann-2')
        cy.get('@ann-2').contains('.ann-username','pornthip')
        cy.get('@ann-2').contains('.ann-button','edit').click()
        cy.wait(200) ;
        cy.url().should('contain','/admin/user/2/edit')
      })

      it('Show the detail of user "pornthip".".',()=>{
        cy.visit('/admin/user/2/edit')
        
        cy.wait(200) ;
        cy.get('.ann-username').should('have.value','pornthip')
        cy.get('.ann-name').should('have.value','Pornthip Sirijutikul')
        cy.get('.ann-email').should('have.value','pornthip.sri@kmutt.ac.th')
        cy.get('.ann-role').should('have.value','announcer')
        cy.get('.ann-created-on').contains('15 Aug 2023')
        cy.get('.ann-created-on').contains('09:30')
        cy.get('.ann-updated-on').contains('15 Aug 2023')
        cy.get('.ann-updated-on').contains('09:30')
      })
});