import { PortalSevenWebPage } from './app.po';

describe('portal-seven-web App', () => {
  let page: PortalSevenWebPage;

  beforeEach(() => {
    page = new PortalSevenWebPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
