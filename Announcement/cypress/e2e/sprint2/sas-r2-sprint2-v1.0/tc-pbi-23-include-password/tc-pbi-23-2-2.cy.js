describe('TC-PBI23-2-1-INCLUDE-PASSWORD\n Senario : create an user: valid password' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it('should have input[type=password] for field password and confirm password',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-password').invoke('attr','type').should('eq','password')
        cy.get('.ann-confirm-password').invoke('attr','type').should('eq','password')
    })

    it('should open add form, type user data and submit the form',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type('Sasowner22#')
        cy.get('.ann-confirm-password').type('Sasowner22#')
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
    })

    it('should create an user',()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('olarn')
        cy.get('.ann-password').type('Sasowner22#')
        cy.get('.ann-confirm-password').type('Sasowner22#')
        cy.get('.ann-name').type('Olarn Rojanapornpun')
        cy.get('.ann-email').type('olarn.roj@kmutt.ac.th')
        cy.get('.ann-role').select('admin')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should have username "olarn".',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('olarn').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-username','olarn')
        cy.get('@ann-1').contains('.ann-name','Olarn')
        cy.get('@ann-1').contains('.ann-name','Rojanapornpun')
        cy.get('@ann-1').contains('.ann-email','olarn.roj@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','admin')
    })

    it('should remove the user : olarn',()=>{
        cy.visit('/admin/user')

        cy.get('.ann-username').contains('olarn').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-username','olarn')
        cy.get('@ann-1').contains('.ann-name','Olarn')
        cy.get('@ann-1').contains('.ann-name','Rojanapornpun')
        cy.get('@ann-1').contains('.ann-email','olarn.roj@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','admin')

        cy.get('@ann-1').contains('.ann-button','delete').should('exist').click()

        cy.on("window:confirm", (message) => {
            expect(message).to.contain("delete");
            return true;
        });
    })
});