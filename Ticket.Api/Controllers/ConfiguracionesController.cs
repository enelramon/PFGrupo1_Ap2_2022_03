using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ConfiguracionesController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public ConfiguracionesController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS

        /* OBTENER CONFIGURACION POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Configuraciones>> GetConfiguraciones(int id)
        {
            var configuraciones = await _context.Configuraciones.FindAsync(id);

            if (configuraciones == null)
            {
                return NotFound();
            }

            return configuraciones;
        }

        /* OBTENER CONFIGURACION POR PERSONALIZACION */
        [HttpGet("{theme},{colorIndex}")]
        public async Task<ActionResult<Configuraciones>> GetConfiguracion(int theme, int colorIndex)
        {
            var configuraciones = await _context.Configuraciones.FirstOrDefaultAsync(c => c.Theme==theme && c.ColorSchemeIndex==colorIndex);

            if (configuraciones == null)
            {
                return NotFound();
            }

            return configuraciones;
        }
        #endregion

    }
}
