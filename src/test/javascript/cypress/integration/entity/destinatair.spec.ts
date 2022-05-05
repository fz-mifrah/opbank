import { entityItemSelector } from '../../support/commands';
import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Destinatair e2e test', () => {
  const destinatairPageUrl = '/destinatair';
  const destinatairPageUrlPattern = new RegExp('/destinatair(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const destinatairSample = { nom: 'Managed', prenom: 'Cotton Island Borders', rib: 72287 };

  let destinatair: any;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/destinatairs+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/destinatairs').as('postEntityRequest');
    cy.intercept('DELETE', '/api/destinatairs/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (destinatair) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/destinatairs/${destinatair.id}`,
      }).then(() => {
        destinatair = undefined;
      });
    }
  });

  it('Destinatairs menu should load Destinatairs page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('destinatair');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response!.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Destinatair').should('exist');
    cy.url().should('match', destinatairPageUrlPattern);
  });

  describe('Destinatair page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(destinatairPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Destinatair page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/destinatair/new$'));
        cy.getEntityCreateUpdateHeading('Destinatair');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', destinatairPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/destinatairs',
          body: destinatairSample,
        }).then(({ body }) => {
          destinatair = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/destinatairs+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [destinatair],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(destinatairPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Destinatair page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('destinatair');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', destinatairPageUrlPattern);
      });

      it('edit button click should load edit Destinatair page', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Destinatair');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', destinatairPageUrlPattern);
      });

      it('last delete button click should delete instance of Destinatair', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('destinatair').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response!.statusCode).to.equal(200);
        });
        cy.url().should('match', destinatairPageUrlPattern);

        destinatair = undefined;
      });
    });
  });

  describe('new Destinatair page', () => {
    beforeEach(() => {
      cy.visit(`${destinatairPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Destinatair');
    });

    it('should create an instance of Destinatair', () => {
      cy.get(`[data-cy="nom"]`).type('View Senior').should('have.value', 'View Senior');

      cy.get(`[data-cy="prenom"]`).type('streamline').should('have.value', 'streamline');

      cy.get(`[data-cy="rib"]`).type('16419').should('have.value', '16419');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(201);
        destinatair = response!.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response!.statusCode).to.equal(200);
      });
      cy.url().should('match', destinatairPageUrlPattern);
    });
  });
});
