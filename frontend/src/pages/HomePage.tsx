import { HeroSection } from "../components/home/HeroSection"
import { FeaturesSection } from "../components/home/FeaturesSection"
import { HowItWorksSection } from "../components/home/HowItWorksSection"
import { CTASection } from "../components/home/CTASection"
import { SiteFooter } from "../components/home/SiteFooter"

function HomePage() {
  return (
    <>
      <HeroSection />
      <FeaturesSection />
      <HowItWorksSection />
      <CTASection />
      <SiteFooter />
    </>
  )
}

export default HomePage
