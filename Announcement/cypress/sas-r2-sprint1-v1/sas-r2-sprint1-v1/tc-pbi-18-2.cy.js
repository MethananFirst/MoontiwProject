describe('TC-PBI18-ADD-USER-NO-AUTHORIZATION-CONTROL-1\n Senario : normal - norminal data', () => {
      
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

    it('should open add form and add a new user "kanokwan".',()=>{
        cy.visit('/admin/user/add')

        cy.wait(200)
        cy.get('.ann-username').type("kanokwan")
        cy.get('.ann-name').type('Kanokwan Suwantavatoop')
        cy.get('.ann-email').type('kanokwan.suw@kmutt.ac.th')
        cy.get('.ann-role').select('announcer')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('Forth user should be username : "kanokwan".',()=>{
        cy.visit('/admin/user')
        
        cy.wait(200) ;
        cy.get('.ann-item').eq(3).as('ann-3')
        cy.get('@ann-3').contains('.ann-username','kanokwan')
        cy.get('@ann-3').contains('.ann-name','Kanokwan Suwantavatoop')
        cy.get('@ann-3').contains('.ann-email','kanokwan.suw@kmutt.ac.th')
        cy.get('@ann-3').contains('.ann-role','announcer')
      })
});