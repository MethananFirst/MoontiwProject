describe('TC-PBI18-ADD-USER-NO-AUTHORIZATION-CONTROL-2\n Senario : normal - norminal data', () => {
      
    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
    }) 

    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        
        cy.wait(200)
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should open add form and add a new user "olarn".',()=>{
        cy.visit('/admin/user/add')

        cy.wait(200)
        cy.get('.ann-username').type("olarn")
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('First user should be username : "olarn".',()=>{
        cy.visit('/admin/user')
        
        cy.wait(200) ;
        cy.get('.ann-item').first().as('ann-1')
        cy.get('@ann-1').contains('.ann-username','olarn')
        cy.get('@ann-1').contains('.ann-name','Olarn Rojanapornpun')
        cy.get('@ann-1').contains('.ann-email','olarn.roj@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','admin')
      })
});