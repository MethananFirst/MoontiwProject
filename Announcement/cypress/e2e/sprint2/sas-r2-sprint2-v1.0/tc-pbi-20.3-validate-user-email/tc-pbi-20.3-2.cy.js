describe('TC-PBI20.3-2-VALIDATE-USER-EMAIL\n Senario : create an user: valid email' , () => {

    it('should visit /admin/user',()=>{
        cy.visit('/admin/user')
    }) 
      
    it('should have add button and click to open url => /admin/user/add',()=>{    
        cy.visit('/admin/user')
        cy.get('.ann-button').contains('add',{matchCase: false}).should('exist').click()
        cy.url().should('contain','/admin/user/add')
    })

    it("should have a validation message: [Email] A part following '@' should not contain the symbol '@'.",()=>{
        cy.visit('/admin/user/add')

        cy.get('.ann-username').type('sunisa')
        cy.get('.ann-password').type("Sasadvisor22*")
        cy.get('.ann-confirm-password').type("Sasadvisor22*")
        cy.get('.ann-name').type('Sunisa Sathapornvajana')
        cy.get('.ann-email').type('sunisa.sat@kmutt.ac.th')
        cy.get('.ann-role').select('announcer')
        cy.get('.ann-button').contains(/submit|save|update|add|edit/g,{matchCase: false}).should('exist').click()
    })

    it('should have username "sunisa".',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('sunisa').parent('.ann-item')
    })

    it('should have username "sunisa".',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('sunisa').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-username','sunisa')
        cy.get('@ann-1').contains('.ann-name','Sunisa')
        cy.get('@ann-1').contains('.ann-name','Sathapornvajana')
        cy.get('@ann-1').contains('.ann-email','sunisa.sat@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','announcer')
    })

    it('should remove the user : sunisa',()=>{
        cy.visit('/admin/user')

        cy.wait(200)
        cy.get('.ann-username').contains('sunisa').parent('.ann-item').as('ann-1')
        cy.get('@ann-1').contains('.ann-username','sunisa')
        cy.get('@ann-1').contains('.ann-name','Sunisa')
        cy.get('@ann-1').contains('.ann-name','Sathapornvajana')
        cy.get('@ann-1').contains('.ann-email','sunisa.sat@kmutt.ac.th')
        cy.get('@ann-1').contains('.ann-role','announcer')

        cy.get('@ann-1').contains('.ann-button','delete').should('exist').click()

        cy.on("window:confirm", (message) => {
            expect(message).to.contain("delete");
            return true;
        });
    })
});